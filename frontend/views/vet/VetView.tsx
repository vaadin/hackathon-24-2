import { AutoGrid } from '@hilla/react-crud';

export default function VetView() {
  return (
    <>
      <section className="flex p-m gap-m items-end">
        <AutoGrid service={Vet} />
      </section>
    </>
  );
}
